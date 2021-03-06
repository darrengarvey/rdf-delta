/*
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *  See the NOTICE file distributed with this work for additional
 *  information regarding copyright ownership.
 */

package org.seaborne.patch.binary.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("all")
public class RDF_IRI implements org.apache.thrift.TBase<RDF_IRI, RDF_IRI._Fields>, java.io.Serializable, Cloneable, Comparable<RDF_IRI> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RDF_IRI");

  private static final org.apache.thrift.protocol.TField IRI_FIELD_DESC = new org.apache.thrift.protocol.TField("iri", org.apache.thrift.protocol.TType.STRING, (short)1);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new RDF_IRIStandardSchemeFactory());
    schemes.put(TupleScheme.class, new RDF_IRITupleSchemeFactory());
  }

  public String iri; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    IRI((short)1, "iri");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // IRI
          return IRI;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.IRI, new org.apache.thrift.meta_data.FieldMetaData("iri", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RDF_IRI.class, metaDataMap);
  }

  public RDF_IRI() {
  }

  public RDF_IRI(
    String iri)
  {
    this();
    this.iri = iri;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RDF_IRI(RDF_IRI other) {
    if (other.isSetIri()) {
      this.iri = other.iri;
    }
  }

  public RDF_IRI deepCopy() {
    return new RDF_IRI(this);
  }

  @Override
  public void clear() {
    this.iri = null;
  }

  public String getIri() {
    return this.iri;
  }

  public RDF_IRI setIri(String iri) {
    this.iri = iri;
    return this;
  }

  public void unsetIri() {
    this.iri = null;
  }

  /** Returns true if field iri is set (has been assigned a value) and false otherwise */
  public boolean isSetIri() {
    return this.iri != null;
  }

  public void setIriIsSet(boolean value) {
    if (!value) {
      this.iri = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case IRI:
      if (value == null) {
        unsetIri();
      } else {
        setIri((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case IRI:
      return getIri();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case IRI:
      return isSetIri();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof RDF_IRI)
      return this.equals((RDF_IRI)that);
    return false;
  }

  public boolean equals(RDF_IRI that) {
    if (that == null)
      return false;

    boolean this_present_iri = true && this.isSetIri();
    boolean that_present_iri = true && that.isSetIri();
    if (this_present_iri || that_present_iri) {
      if (!(this_present_iri && that_present_iri))
        return false;
      if (!this.iri.equals(that.iri))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(RDF_IRI other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetIri()).compareTo(other.isSetIri());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIri()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.iri, other.iri);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("RDF_IRI(");
    boolean first = true;

    sb.append("iri:");
    if (this.iri == null) {
      sb.append("null");
    } else {
      sb.append(this.iri);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (iri == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'iri' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class RDF_IRIStandardSchemeFactory implements SchemeFactory {
    public RDF_IRIStandardScheme getScheme() {
      return new RDF_IRIStandardScheme();
    }
  }

  private static class RDF_IRIStandardScheme extends StandardScheme<RDF_IRI> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RDF_IRI struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // IRI
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.iri = iprot.readString();
              struct.setIriIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, RDF_IRI struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.iri != null) {
        oprot.writeFieldBegin(IRI_FIELD_DESC);
        oprot.writeString(struct.iri);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RDF_IRITupleSchemeFactory implements SchemeFactory {
    public RDF_IRITupleScheme getScheme() {
      return new RDF_IRITupleScheme();
    }
  }

  private static class RDF_IRITupleScheme extends TupleScheme<RDF_IRI> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RDF_IRI struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.iri);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RDF_IRI struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.iri = iprot.readString();
      struct.setIriIsSet(true);
    }
  }

}

