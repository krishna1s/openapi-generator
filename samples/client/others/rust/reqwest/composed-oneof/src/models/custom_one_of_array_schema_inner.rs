/*
 * oneOf test
 *
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 * Generated by: https://openapi-generator.tech
 */

use super::ObjA;
use super::ObjB;
use super::ObjC;


#[derive(Clone, Debug, PartialEq, Serialize, Deserialize)]
#[serde(tag = "realtype")]
pub enum CustomOneOfArraySchemaInner {
    ObjA(Box<ObjA>),
    ObjB(Box<ObjB>),
    ObjC(Box<ObjC>),
}

impl Default for CustomOneOfArraySchemaInner {
    fn default() -> Self {
        Self::ObjA(Box::default())
    }
}




